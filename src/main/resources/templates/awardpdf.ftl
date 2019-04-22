\documentclass{article}

<#--

\begin{filecontents*}{\jobname.64}
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
\end{filecontents*}
\usepackage{graphicx}

-->

\begin{document}

<#-- \immediate\write18{base64 --decode \jobname.64 > \jobname-tmp.pdf} -->

${recipientName} has been awarded ${awardName} by ${granterName} on ${dateAwarded}

<#if hasSignature>
    there IS a signature.
<#else>
    there is NOT a signature.
</#if>

<#-- \fbox{\includegraphics[width=3cm]{\jobname-tmp.pdf}} -->
\end{document}